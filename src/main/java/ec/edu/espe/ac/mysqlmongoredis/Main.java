package ec.edu.espe.ac.mysqlmongoredis;

import com.mongodb.MongoClient;
import ec.edu.espe.ac.model.RegistroCivil;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.apache.commons.io.FileUtils;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author jhona
 */
public class Main {

    public static void main(String[] args) throws ParseException {
        System.out.println("Deber: LechonJ - TambacoJ - PicoV");
        System.out.println("Creacion BD");
        Persistence.generateSchema("ec.edu.espe.ac_MysqlMongoRedis_jar_1PU", null);
        System.out.println("Tiempos:---------------------------------------");
        System.out.println("1: Insertar Archivo en Mysql");
        StringBuilder sb = new StringBuilder();
        long start = System.currentTimeMillis();
        Cargar();
        long end = System.currentTimeMillis();
        System.out.println("Insert en Mysql time: " + (end - start));
      

    }

    public static void Cargar() throws ParseException {
        int cont = 1;
        String file = "c:\\tmp\\RegistroCivil.txt";
        System.out.println("Cargando archivo " + file);
        File fileMora = new File(file);
        List<String> readLines = new ArrayList<String>();
        try {
            readLines = verificateFiles(fileMora);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (readLines != null) {
            transferData(readLines);
        };
        cont++;
    }

    private static List<String> verificateFiles(File file) throws IOException {
        List<String> readLines;
        if (file.exists()) {
            readLines = FileUtils.readLines(file, "UTF-8");
        } else {

            readLines = null;
        }
        return readLines;
    }

    private static void transferData(List<String> readSource) throws ParseException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ec.edu.espe.ac_MysqlMongoRedis_jar_1PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaccion = em.getTransaction();
        Iterator iter = readSource.iterator();
        int batchSize = 1000;
        String[] values;
        int i = 0;
        transaccion.begin();
        while (iter.hasNext()) {
            RegistroCivil ct = new RegistroCivil();
            values = iter.next().toString().split(",");
            ct.setCedula(values[0]);
            ct.setApellidos(values[1]);
            ct.setNombres(values[2]);
            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
            Date date = sdf.parse(values[3]);
            ct.setFechaNacimiento(date);
            ct.setCodProvincia(Integer.valueOf(values[4]));
            ct.setGenero(values[5]);
            ct.setCodEstadoCivil(values[6]);
            em.persist(ct);
            i++;

            if (i % batchSize == 0) {
                System.out.println(i);
                em.flush();
                em.clear();
                transaccion.commit();
                transaccion.begin();
            }

        }
       
        transaccion.commit();
        System.out.println("Insertando en mongo: ");
        long start = System.currentTimeMillis();
        Morphia morphia = new Morphia();
        morphia.mapPackage("ec.edu.espe.ac.model.registroCivilM");
        Datastore ds = morphia.createDatastore(new MongoClient(), "regCivil");
        System.out.println("Conexion establecida");
        try {
            TypedQuery<RegistroCivil> consulta = em.createQuery("select p from RegistroCivil p", RegistroCivil.class);
            List<RegistroCivil> lista = consulta.getResultList();
            for (RegistroCivil e : lista) {
                ds.save(e);
                ds.ensureIndexes();
            }            
        } catch (Exception e) {
            System.out.println("E: " + e);
        }
        long end = System.currentTimeMillis();
        System.out.println("Insert en MongoDB time: " + (end - start));
        em.close();
    }
}
