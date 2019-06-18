package de.rvwbk.eit72.bombenentschaerfenapp.walkthrough;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import de.rvwbk.eit72.bombenentschaerfenapp.R;
import de.rvwbk.eit72.bombenentschaerfenapp.overview.OverviewActivity;

public class SlideAdapter extends PagerAdapter {
    private Context context;

    // list of images
    private int[] lst_images = {
            R.drawable.school,
            R.drawable.bomb_new,
            R.drawable.map_new,
            R.drawable.hero_new
    };
    // list of titles
    private String[] lst_title = {
            "SCHULE",
            "BOMBE",
            "AUFGABEN",
            "RETTUNG"
    }   ;
    // list of descriptions
    private String[] lst_description = {
            "Herzlich Willkommen am Richard-von-Weizsäcker Berufskolleg in Paderborn. Zur Begrüßung und zum näheren Kennenlernen der Schule haben wir eine Art „interaktive Schnitzeljagd“ für dich vorbereitet.  ",
            "Das RVWBK und seine Schüler schweben nämlich in großer Gefahr! Eine unbekannte Organisation hat eine Bombe in der Schule versteckt. Nur DU allein kannst dabei helfen, diese Bomben zu entschärfen. Aber wie genau funktioniert das?",
            "In der Schule sind Beacons (Signalstationen) versteckt, welche gesucht werden müssen. Diese Beacons haben verschiedene Aufgaben hinterlegt, welche nach erfolgreicher Lösung mit einem Codeschnipsel belohnt werden! ",
            "Wenn alle Aufgaben erfolgreich bearbeitet wurden ergibt sich ein Lösungswort aus allen Codeschnipseln. Mit diesem Lösungswort kann die Bombe entschärft und DU zum Helden des gesamten RVWBK gemacht werden! "
    };
    // list of background colors
    private int[]  lst_backgroundcolor = {
            Color.rgb(53, 57, 144),
            Color.rgb(217, 10, 30),
            Color.rgb(103, 0, 153),
            Color.rgb(49, 205, 253)
    };


    SlideAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return lst_title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, Object object) {
        return (view == object);
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view;
        ConstraintLayout layoutslide;
        if(getCount() != position + 1) {
            view = inflater.inflate(R.layout.slide,container,false);
            layoutslide = view.findViewById(R.id.slidelinearlayout);
        } else {
            view = inflater.inflate(R.layout.slide_last,container,false);
            layoutslide = view.findViewById(R.id.lastslidelinearlayout);

            Button button = (Button) view.findViewById(R.id.startgame);

            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(context, OverviewActivity.class);
                    context.startActivity(intent);
                }
            });
        }

        ImageView imgslide = (ImageView)  view.findViewById(R.id.slideimg);
        TextView txttitle= (TextView) view.findViewById(R.id.txttitle);
        TextView description = (TextView) view.findViewById(R.id.txtdescription);
        layoutslide.setBackgroundColor(lst_backgroundcolor[position]);
        imgslide.setImageResource(lst_images[position]);
        txttitle.setText(lst_title[position]);
        description.setText(lst_description[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
