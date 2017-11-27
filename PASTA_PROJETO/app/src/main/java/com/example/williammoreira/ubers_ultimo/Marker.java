package com.example.williammoreira.ubers_ultimo;


/**
 * Created by Wander on 04/09/2017.
 */

public class Marker {

    private Double lat;
    private Double lon;
    private String description;
    private String name;
    private String hfun;
    private String endereco;
    private String telefone;
    private String servico;
    private String profissional;

    public Marker(Double l, Double lo, String desc , String n){
        lat = l;
        lon = lo;
        description = desc;
        name= n;
    }





    public Marker (Double l, Double lo, String desc, String n, String end, String hf, String tel, String serv, String prof)
    {
        lat = l;
        lon= lo;
        description = desc;
        name= n ;
        hfun = hf;
        endereco = end;
        telefone= tel;
        servico = serv;
        profissional = prof;

    }

    public Marker (Double l, Double lo, String desc, String n, String end, String hf, String tel)
    {
        lat = l;
        lon= lo;
        description = desc;
        name= n ;
        hfun = hf;
        endereco = end;
        telefone= tel;

    }

    public String getEndereco() { return endereco; }

    public String getHorarioFunc() { return hfun; }

    public String getTel(){return telefone;}

    public String getServico(){return servico;}

    public String getDesc() {return description;}

    public String getProfissional() {return profissional;}

    public String getName() {return name;}

    public Double getLat() {return lat;}

    public Double getLon() {return lon;}


}
