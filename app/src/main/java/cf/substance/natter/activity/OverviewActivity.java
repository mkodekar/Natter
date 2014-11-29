package cf.substance.natter.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

	private static final String[]

		INBOX_PROJECTION = new String[]{
			Telephony.Sms.Inbox.ADDRESS,
			Telephony.Sms.Inbox.DATE,
			Telephony.Sms.Inbox.BODY },

		CONTACT_PROJECTION = new String[]{
			ContactsContract.PhoneLookup.DISPLAY_NAME,
			ContactsContract.Data.PHOTO_THUMBNAIL_URI
		};

	private Cursor conversations;
	private RecyclerView.Adapter adapter;

	@InjectView( R.id.list ) RecyclerView list;

	//--------------------------------------------------------------------------

	@Override protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_overview );

		ButterKnife.inject( this );

		updateSmsList();

		adapter = new DemoAdapter();
		list.setLayoutManager( new LinearLayoutManager( this ));
		list.setAdapter( adapter );
	}

	//--------------------------------------------------------------------------

	@Override public boolean onCreateOptionsMenu( Menu menu ) {
		getMenuInflater().inflate( R.menu.activity_overview, menu );
		return true;
	}

	//--------------------------------------------------------------------------

	@Override public boolean onOptionsItemSelected( MenuItem item ) {
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

	private void updateSmsList() {
		conversations = getContentResolver().query(
			Telephony.Sms.Inbox.CONTENT_URI,
			INBOX_PROJECTION,
			null,
			null,
			Telephony.Sms.Inbox.DEFAULT_SORT_ORDER );
		if ( adapter != null ) adapter.notifyDataSetChanged();
	}

	//--------------------------------------------------------------------------

	//==========================================================================
	class DemoAdapter extends RecyclerView.Adapter<ViewHolder> {
		//----------------------------------------------------------------------

		@Override public int getItemCount() {
			try { return conversations.getCount(); }
			catch( Exception e ) { return 0; }
		}

		//----------------------------------------------------------------------

		@Override public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
			LayoutInflater inflater = LayoutInflater.from( parent.getContext() );
			return new ViewHolder( inflater.inflate( R.layout.listitem_twoline_withavatar, parent, false ));
		}

		//----------------------------------------------------------------------

		@Override public void onBindViewHolder( ViewHolder holder, int position ) {
			conversations.moveToPosition( position );

			holder.avatar.setBackgroundColor( getResources().getColor( R.color.black_a12 ));

			// Sender name and picture
			// TODO This is astonishingly inefficient!
			Uri uri = Uri.withAppendedPath( ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode( conversations.getString( 0 )));
			Log.d( "Natter", "Uri = " + uri );
			Cursor c = getContentResolver().query(
				uri,
				CONTACT_PROJECTION,
				null, null, null );
			if ( c.moveToFirst() ) {
				holder.primary.setText( c.getString( 0 ));
				holder.avatar.setImageURI( Uri.parse( c.getString( 1 )));

			} else {
				holder.primary.setText( "failed to get name" );
			}
			c.close();

			holder.secondary.setText( conversations.getString( 2 ));
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
