package com.monitora.android.nufscar.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.monitora.android.nufscar.R;
import com.monitora.android.nufscar.model.Eventos;
import com.monitora.android.nufscar.view.fragment.EventsFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Intent;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;


public class EventsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Eventos event = (Eventos) extras.getSerializable(EventsFragment.KEY_IDEVENTO);

            TextView eventsTitle = (TextView) findViewById(R.id.events_details_title);
            TextView eventsHost = (TextView) findViewById(R.id.events_details_host);
            TextView eventsDate = (TextView) findViewById(R.id.events_details_date);
            TextView eventsText = (TextView) findViewById(R.id.events_details_text);

            eventsTitle.setText(event.getTitulo());
            eventsDate.setText("Data do evento: " + event.getData());
            eventsHost.setText(event.getLocal());
            eventsText.setText(event.getTexto());
        }
    }

    public void onAddEventClicked(View view){

        long lnsTime=0, lneTime;

        Date dateObject;

        Bundle extras = getIntent().getExtras();
        Eventos event = (Eventos) extras.getSerializable(EventsFragment.KEY_IDEVENTO);

        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            String dob_var = event.getData();

            dateObject = formatter.parse(dob_var);

            lnsTime = dateObject.getTime();
            //Log.e(null, Long.toString(lnsTime));
        }
        catch (java.text.ParseException e)
        {
            e.printStackTrace();
            //Log.i("E11111111111", e.toString());
        }

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, lnsTime);
//        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endCal.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        intent.putExtra(Events.TITLE, event.getTitulo());
        intent.putExtra(Events.DESCRIPTION,  event.getTexto());
        intent.putExtra(Events.EVENT_LOCATION, event.getLocal().substring(7));
        intent.putExtra(Events.RRULE, "FREQ=YEARLY");
        intent.putExtra(Events.VISIBLE, 1);

        startActivity(intent);
    }
    
}
