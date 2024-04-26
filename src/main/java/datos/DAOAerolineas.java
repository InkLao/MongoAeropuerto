/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;
import modelos.Aerolinea;
import org.bson.types.ObjectId;

/**
 *
 * @author lv1825
 */
public class DAOAerolineas {

    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;

    public DAOAerolineas() {
        mongoClient = new MongoClient();
        database = mongoClient.getDatabase("airport_228558");
        collection = database.getCollection("airlines");
    }

    public ArrayList<Aerolinea> obtenerAerolineas() {
        ArrayList<Aerolinea> aerolineas = new ArrayList();
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document d = cursor.next();
                Aerolinea a = new Aerolinea(d.getObjectId("_id"), d.getString("name"), d.getString("country"), d.getString("currency"), d.getBoolean("lowcost") == null ? false : true);
                aerolineas.add(a);
                //System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
        return aerolineas;
    }
    
    public void agregarAerolinea(Aerolinea aerolinea) {
        Document doc = new Document("name", aerolinea.getNombre())
            .append("country", aerolinea.getPais())
            .append("currency", aerolinea.getMoneda())
            .append("lowcost", aerolinea.isEconomica());
        collection.insertOne(doc);
    }

    public boolean eliminarAerolinea(String id) {
        ObjectId objectId;
        try {
            objectId = new ObjectId(id);
        } catch(IllegalArgumentException e) {
            System.out.println("El ID proporcionado no es válido.");
            return false;
        }
        
        DeleteResult result = collection.deleteOne(eq("_id", objectId));
        return result.getDeletedCount() > 0;
    }
}
