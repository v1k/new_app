package com.v1k.lifehelper;

import java.util.List;
import java.util.Random;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.View;
import android.widget.ArrayAdapter;

public class ListsActivity extends ListActivity {

	private DataSources datasource;

	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_lists);

	    datasource = new DataSources(this);
	    datasource.open();

	    List<MyList> values = datasource.getAllMyLists();

	    // Use the SimpleCursorAdapter to show the
	    // elements in a ListView
	    ArrayAdapter<MyList> adapter = new ArrayAdapter<MyList>(this,
	        android.R.layout.simple_list_item_1, values);
	    setListAdapter(adapter);
	  }

	  // Will be called via the onClick attribute
	  // of the buttons in main.xml
	  public void onClick(View view) {
	    @SuppressWarnings("unchecked")
	    ArrayAdapter<MyList> adapter = (ArrayAdapter<MyList>) getListAdapter();
	    MyList list = null;
	    switch (view.getId()) {
	    case R.id.add:
	      String[] lists = new String[] { "Cool", "Very nice", "Hate it" };
	      int nextInt = new Random().nextInt(3);
	      // Save the new comment to the database
	      list = datasource.createMyList(lists[nextInt]);
	      adapter.add(list);
	      break;
	    case R.id.delete:
	      if (getListAdapter().getCount() > 0) {
	        list = (MyList) getListAdapter().getItem(0);
	        datasource.deleteMyList(list);
	        adapter.remove(list);
	      }
	      break;
	    }
	    adapter.notifyDataSetChanged();
	  }

	  @Override
	  protected void onResume() {
	    datasource.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
	    datasource.close();
	    super.onPause();
	  }

}
