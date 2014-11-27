package cf.substance.natter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cf.substance.natter.R;

//==============================================================================
public class OverviewActivity extends ActionBarActivity {
	//--------------------------------------------------------------------------

	@InjectView( R.id.list ) RecyclerView list;

	//--------------------------------------------------------------------------

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_overview );

		ButterKnife.inject( this );

		list.setLayoutManager( new LinearLayoutManager( this ));
		list.setAdapter( new DemoAdapter() );
	}

	//--------------------------------------------------------------------------

	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {
		getMenuInflater().inflate( R.menu.activity_overview, menu );
		return true;
	}

	//--------------------------------------------------------------------------

	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {
		switch( item.getItemId() ) {

			case R.id.action_archived:
				startActivity( new Intent( this, ArchivedActivity.class ));
				return true;

			case R.id.action_settings:
				startActivity( new Intent( this, SettingsActivity.class ));
				return true;

			case R.id.action_helpAndFeedback:
				startActivity( new Intent( this, HelpAndFeedbackActivity.class ));
				return true;
		}

		return super.onOptionsItemSelected( item );
	}

	//--------------------------------------------------------------------------

	//==========================================================================
	class DemoAdapter extends RecyclerView.Adapter<ViewHolder> {
		//----------------------------------------------------------------------

		@Override public int getItemCount() {
			return 3;
		}

		//----------------------------------------------------------------------

		@Override public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
			LayoutInflater inflater = LayoutInflater.from( parent.getContext() );
			return new ViewHolder( inflater.inflate( R.layout.listitem_twoline_withavatar, parent, false ));
		}

		//----------------------------------------------------------------------

		@Override public void onBindViewHolder( ViewHolder holder, int position ) {
			holder.avatar.setBackgroundColor( getResources().getColor( R.color.black_a12 ));
			holder.primary.setText( "Item " + position );
			holder.secondary.setText( "Lorem slipstream Dolan sits if he fits" );
		}

		//----------------------------------------------------------------------
	}
	//--------------------------------------------------------------------------

	//==========================================================================
	class ViewHolder extends RecyclerView.ViewHolder {
		//----------------------------------------------------------------------

		int index;

		@InjectView( R.id.avatar    ) ImageView avatar;
		@InjectView( R.id.primary   ) TextView primary;
		@InjectView( R.id.secondary ) TextView secondary;

		//----------------------------------------------------------------------

		public ViewHolder( View itemView ) {
			super( itemView );
			ButterKnife.inject( this, itemView );
		}

		//----------------------------------------------------------------------

		@OnClick( R.id.item )
		void onItemClick() {
			startActivity( new Intent( OverviewActivity.this, ConversationActivity.class ));
		}

		//----------------------------------------------------------------------
	}
	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
