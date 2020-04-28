package com.example.tp_transverse_memos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.tp_transverse_memos.bdd.AppDatabaseHelper;
import com.example.tp_transverse_memos.objects.MemoDTO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener{

    private RecyclerView recyclerView = null;
    private EditText editTextMemo = null;
    private FrameLayout frameLayoutConteneurDetail = null;

    // Adapter :
    private MemosAdapter memosAdapter = null;

    // Gesture detector :
    private GestureDetector gestureDetector = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // init :
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabaseHelper.getDatabase(this);

        // vues :
        recyclerView = findViewById(R.id.liste_memos);
        editTextMemo = findViewById(R.id.saisie_memo);
        frameLayoutConteneurDetail = findViewById(R.id.conteneur_detail);

        // à ajouter pour de meilleures performances :
        recyclerView.setHasFixedSize(true);

        // layout manager, décrivant comment les items sont disposés :
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        // contenu d'exemple :
        List<MemoDTO> listeMemos = AppDatabaseHelper.getDatabase(this)
                .memosDAO()
                .getListeMemos();

        // adapter :
        memosAdapter = new MemosAdapter(listeMemos);
        recyclerView.setAdapter(memosAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(memosAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        // listener :
        recyclerView.addOnItemTouchListener(this);
        gestureDetector = new GestureDetector(this,
                new GestureDetector.SimpleOnGestureListener()
                {
                    @Override
                    public boolean onSingleTapUp(MotionEvent event)
                    {
                        return true;
                    }
                });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent motionEvent)
    {
        if (gestureDetector.onTouchEvent(motionEvent))
        {
            // récupération de l'item cliqué :
            View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            if (child != null)
            {
                // position dans la liste d'objets métier (position à partir de zéro !) :
                int position = recyclerView.getChildAdapterPosition(child);

                // récupération du mémo à cette position :
                MemoDTO memo = memosAdapter.getItem(position);

                // affichage du détail :
                if (frameLayoutConteneurDetail != null)
                {
                    // fragment :
                    DetailFragment fragment = new DetailFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString(DetailFragment.EXTRA_MEMO, memo.getIntitule());
                    fragment.setArguments(bundle);

                    // le conteneur de la partie détail est disponible, on est donc en mode "tablette" :
                    getSupportFragmentManager().beginTransaction().replace(R.id.conteneur_detail, fragment).commit();


                }
                else
                {
                    // le conteneur de la partie détail n'est pas disponible, on est donc en mode "smartphone" :
                    Intent intent = new Intent(this, DetailActivity.class);
                    intent.putExtra(DetailFragment.EXTRA_MEMO, memo.getIntitule());
                    startActivity(intent);
                }

                return true;
            }
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public void onClickBoutonValider(View view)
    {
        MemoDTO memo = new MemoDTO(editTextMemo.getText().toString());
        // ajout du mémo :
        memosAdapter.ajouterMemo(memo);
        AppDatabaseHelper.getDatabase(this).memosDAO().insert(memo);

        // animation de repositionnement de la liste (sinon on ne voit pas l'item ajouté) :
        recyclerView.smoothScrollToPosition(0);

        // on efface le contenu de la zone de saisie :
        editTextMemo.setText("");
    }
}
