package pom.poly.com.trysimpleprovider;


import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.btInput)
    Button btInput;
    @BindView(R.id.btRefresh)
    Button btRefresh;
    @BindView(R.id.lvShowpeople)
    ListView lvShowpeople;
    @BindView(R.id.edName)
    EditText edName;
    @BindView(R.id.edYearsOld)
    EditText edYearsOld;
    private PeopleINFProvider peopleInf;
    private SimpleCursorAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//         peopleInf = new PeopleINFProvider();

        setupCursorAdapter();
        lvShowpeople.setAdapter(adapter);
    }

    @OnClick({R.id.btInput, R.id.btRefresh})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btInput:
                inputData();
                break;
            case R.id.btRefresh:
                getSupportLoaderManager().initLoader(0,
                        new Bundle(), this);
                break;
        }
    }

    private void inputData() {
        Uri uri = Uri.parse("content://pom.poly.com.trysimpleprovider/persons");
        ContentValues values = new ContentValues();
        values.put(PeopleINFProvider.Person.KEY_NAME, edName.getText() + "");
        values.put(PeopleINFProvider.Person.KEY_YEARDOLD, edYearsOld.getText() + "");
        getContentResolver().insert(uri, values);
    }

    private void setupCursorAdapter() {
        // Column data from cursor to bind views from
        String[] uiBindFrom = {PeopleINFProvider.Person.KEY_NAME,
                PeopleINFProvider.Person.KEY_YEARDOLD};
        // View IDs which will have the respective column data inserted
        int[] uiBindTo = {R.id.tvIteamName, R.id.tvIteamYear};
        // Create the simple cursor adapter to use for our list
        // specifying the template to inflate (item_contact),
        adapter = new SimpleCursorAdapter(
                this, R.layout.item_content,
                null, uiBindFrom, uiBindTo,
                0);
    }

    @Override
    public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = Uri.parse("content://pom.poly.com.trysimpleprovider/persons");
        return new CursorLoader(this, uri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
