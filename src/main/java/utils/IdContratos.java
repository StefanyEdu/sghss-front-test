package utils;

import java.net.MalformedURLException;
import java.net.URL;

public class IdContratos {
    public String extrairID(String urlString) {
        try {
            URL url = new URL(urlString);
            String path = url.getPath();

            String[] partesDoCaminho = path.split("/");
            String id = partesDoCaminho[partesDoCaminho.length - 2];

            return id;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public  String idContratoUrl (String urlContrato) {
        String urlExemplo =urlContrato ;
        String idExtraido = extrairID(urlExemplo);

        if (idExtraido != null) {
            System.out.println("ID extraído: " + idExtraido);
        } else {
            System.out.println("Não foi possível extrair o ID.");
        }
        return idExtraido;
    }
    public String extrairIDAditivo(String urlString) {
        try {
            URL url = new URL(urlString);
            String path = url.getPath();


            String[] partesDoCaminho = path.split("/");
            String id = partesDoCaminho[partesDoCaminho.length - 2];

            return id;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public  String idContratoUrlAditivo (String urlContrato) {
        String urlExemplo =urlContrato ;
        String idExtraido = extrairIDAditivo(urlExemplo);

        if (idExtraido != null) {
            System.out.println("ID extraído: " + idExtraido);
        } else {
            System.out.println("Não foi possível extrair o ID.");
        }
        return idExtraido;
    }

}
