package com.example.experiment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.experiment2.word.WordContent;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ItemFragment.OnItemClickListener{

    private int id = 0;
    private String word;
    private String meaning;
    private String sample;
    private WordDao wordDao;
    private static volatile WordBean wordBean;
    ArrayList<WordBean> wordList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        wordDao = db.wordDao();

        createList(MainActivity.this);

        Button insert = (Button) findViewById(R.id.insert);
        Button check = (Button) findViewById(R.id.check);
        Button delete = (Button) findViewById(R.id.delete);



        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText wordET = (EditText) findViewById(R.id.word);
                word = wordET.getText().toString();
                EditText meanET = (EditText) findViewById(R.id.mean);
                meaning = meanET.getText().toString();
                EditText sampleET = (EditText) findViewById(R.id.sample);
                sample = sampleET.getText().toString();
                insert(MainActivity.this);
                ItemFragment itemFragment = (ItemFragment) getSupportFragmentManager().findFragmentById(R.id.wordsList);
                itemFragment.onResume();
            }
        });



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText wordET = (EditText) findViewById(R.id.word);
                word = wordET.getText().toString();
                delete(MainActivity.this);
                ItemFragment itemFragment = (ItemFragment) getSupportFragmentManager().findFragmentById(R.id.wordsList);
                itemFragment.onResume();
                refresh();
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText wordET = (EditText) findViewById(R.id.word);
                word = wordET.getText().toString();
                check(MainActivity.this);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                onItemClick(WordContent.ITEM_MAP.get(wordBean.id+""));
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.help:
                showHelp();
                return true;
            case R.id.exit:
                finish();
                return true;
            default:
                return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public void showHelp() {
        Toast.makeText(MainActivity.this, "help", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onItemClick(WordContent.WordItem wordItem) {
        DetailFragment detailFragment = (DetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.wordDetail);
        if (detailFragment != null) detailFragment.updateView(wordItem);
        else {
            DetailFragment newFragment = new DetailFragment();
            Bundle args = new Bundle();
            args.putSerializable(DetailFragment.ARG_PARAM1, wordItem);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.wordDetail, newFragment);
            transaction.commit();
        }
    }

    private void insert(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(wordDao.findByWord(word) == null) {
                    wordBean = new WordBean(id, word, meaning, sample);
                    WordContent.addItem(id + "", word, meaning, sample);
                    wordDao.insert(wordBean);
                    id++;
                } else {
                    wordBean = wordDao.findByWord(word);
                    WordContent.deleteItem(wordBean.id+"",wordBean.word,wordBean.meaning,wordBean.sample);
                    wordBean.setMeaning(meaning);
                    wordBean.setSample(sample);
                    WordContent.addItem(wordBean.id+"",wordBean.word,wordBean.meaning,wordBean.sample);
                    wordDao.update(wordBean);
                }
            }
        }).start();
    }

    private void delete(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                wordBean = wordDao.findByWord(word);
                WordContent.deleteItem(id+"",word,meaning,sample);
                wordDao.delete(wordBean);
            }
        }).start();
    }

    private void check(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                wordBean = wordDao.check(word);
            }
        }).start();
    }

    private void createList(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                wordList = (ArrayList<WordBean>) wordDao.getAll();
                WordContent.deleteAll();
                for(id = 0; id < wordList.size(); id++) {
                    WordContent.addItem(wordList.get(id).id+"",wordList.get(id).word,wordList.get(id).meaning,wordList.get(id).sample);
                }

            }
        }).start();
    }

    private void refresh() {
        finish();
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        onCreate(null);
    }
}