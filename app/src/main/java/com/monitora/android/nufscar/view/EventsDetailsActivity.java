package com.monitora.android.nufscar.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.monitora.android.nufscar.R;
import com.monitora.android.nufscar.model.Eventos;
import com.monitora.android.nufscar.view.fragment.EventsFragment;

import org.w3c.dom.Text;

//IMPORTS CALENDÁRIO
import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import java.util.Calendar;
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
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");

        Calendar cal = Calendar.getInstance();
        long startTime = cal.getTimeInMillis();
        long endTime = cal.getTimeInMillis()  + 60 * 60 * 1000;

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,endTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        intent.putExtra(Events.TITLE, "Meu evento criado");
        intent.putExtra(Events.DESCRIPTION,  "Descrição simples do meu evento");
        intent.putExtra(Events.EVENT_LOCATION, "Departamento computação");
        intent.putExtra(Events.RRULE, "FREQ=YEARLY");

        startActivity(intent);
    }


}
