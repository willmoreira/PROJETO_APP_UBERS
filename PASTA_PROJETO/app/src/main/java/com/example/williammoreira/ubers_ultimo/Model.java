package com.example.williammoreira.ubers_ultimo;


        import android.app.FragmentManager;
        import android.graphics.Camera;
        import android.graphics.Color;
        import android.location.Location;
        import android.location.LocationListener;
        import android.os.Bundle;

        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.MarkerOptions;
        import com.google.android.gms.maps.model.Polyline;
        import com.google.android.gms.maps.model.PolylineOptions;

        import org.w3c.dom.Document;

        import java.lang.Math;

        import java.text.DecimalFormat;
        import java.util.ArrayList;
        import java.util.List;


public class Model implements LocationListener {

    private GoogleMap map;
    private List<Marker> otherMarkers;
    private Calculation calc = new Calculation();
    private LatLng focus;
    private LatLng maisproximo;
    Location local;

    Direction md = new Direction();


    public Model(GoogleMap map, List<Marker> markers){

        this.map = map;
        this.otherMarkers = markers;

    }


    @Override
    public void onLocationChanged(Location location) {
        Marker m = new Marker(location.getLatitude(), location.getLongitude(), "","");
        // Marker m = new Marker(location.getLatitude(), location.getLongitude(), "","","","",""); // usando mais argumentos
        Double distance = calc.CalculationByDistance(new LatLng(location.getLatitude(), location.getLongitude()), new LatLng(this.otherMarkers.get(0).getLat(), this.otherMarkers.get(0).getLon()));

        double menor = distance.intValue(); int aux=0;Integer km=0; // Variaveis de auxilio, obs: km está ali para eu ter um valor caso nao queira o menor.
        for ( int i =0; i < otherMarkers.size(); i ++) {
            distance= calc.CalculationByDistance(new LatLng(location.getLatitude(), location.getLongitude()), new LatLng(this.otherMarkers.get(i).getLat(), this.otherMarkers.get(i).getLon()));

            if (menor < distance) {
                km = distance.intValue();
            }
            else {
                Math.round(distance); // Arredondando valor pra cima
                menor= distance;
                aux = i; // guardando a posição com o menor valor, para saber a qual UBS pertence.
            }

        }
        DecimalFormat df=  new DecimalFormat("#.0"); //formatando casas decimais;
        // Adicionando a localização atual junto da distancia da UBS mais proxima junto de seu Nome e Exibindo.
        map.addMarker(new MarkerOptions().position(new LatLng(m.getLat(), m.getLon())).title("A " +   df.format(menor) + "KM de " + this.otherMarkers.get(aux).getName())).showInfoWindow();
        focus = new LatLng(m.getLat(),m.getLon());
        maisproximo= new LatLng(this.otherMarkers.get(aux).getLat(),this.otherMarkers.get(aux).getLon());




        Document doc = md.getDocument(focus, maisproximo,
                Direction.MODE_DRIVING);


        ArrayList<LatLng> directionPoint = md.getDirection(doc);
        PolylineOptions rectLine = new PolylineOptions().width(8).color(
                Color.BLUE);

        for (int i = 0; i < directionPoint.size(); i++) {
            rectLine.add(directionPoint.get(i));
        }
        Polyline polylin = map.addPolyline(rectLine);





        map.animateCamera(CameraUpdateFactory.newLatLngZoom(focus,14));


    }
    @Override
    public void onProviderDisabled(String provider) {
    }
    @Override
    public void onProviderEnabled(String provider) {
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }



}
