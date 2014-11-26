package cf.substance.natter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.InjectView;

//==============================================================================
public class HomeActivity extends ActionBarActivity {
	//--------------------------------------------------------------------------

	@InjectView( R.id.list ) RecyclerView list;

	//--------------------------------------------------------------------------

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_home );

		ButterKnife.inject( this );

		list.setLayoutManager( new LinearLayoutManager( this ));
	}

	//--------------------------------------------------------------------------

	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {
		getMenuInflater().inflate( R.menu.activity_home, menu );
		return true;
	}

	//--------------------------------------------------------------------------

	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {
		switch( item.getItemId() ) {
			case R.id.action_settings:
			return true;
		}

		return super.onOptionsItemSelected( item );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
