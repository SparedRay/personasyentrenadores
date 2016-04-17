/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backand;

import java.util.*;
import Humanidad.*;
import com.firebase.client.*;
import java.util.concurrent.CountDownLatch;
/**
 *
 * @author Martinor
 */
public class Bconnector {
    
    private CountDownLatch latch;
    private String hostUrl;
    private Firebase db;
    
    public Bconnector(String hostUrl, CountDownLatch latch) {
        this.hostUrl = hostUrl;
        this.latch = latch;
        db = new Firebase(hostUrl);
    }
    
    public Bconnector() {
        this("", new CountDownLatch(1));
    }

    public ArrayList<Persona> getData(String tipo) {
        ArrayList<Persona> personasDb = new ArrayList<>();

        db.child(tipo).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(DataSnapshot persona : snapshot.getChildren()) {
                    Persona p;
                    switch(tipo) {
                        case "Entrenadores":
                            p = persona.getValue(Entrenador.class);
                            break;
                        case "Deportistas":
                            p = persona.getValue(Deportista.class);
                            break;
                        default:
                            p = persona.getValue(Persona.class);
                            break;
                    }
                    // Evento solo para hacer tiempo.
                }
                latch.countDown();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("No se pudo obtener datos del servidor: " + firebaseError);
            }
        });

        db.child(tipo).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
                int index = Integer.parseInt(snapshot.getKey());
                Persona p;
                switch(tipo) {
                    case "Entrenadores":
                        p = snapshot.getValue(Entrenador.class);
                        break;
                    case "Deportistas":
                        p = snapshot.getValue(Deportista.class);
                        break;
                    default:
                        p = snapshot.getValue(Persona.class);
                        break;
                }
                if(!personasDb.contains(p)) {
                    personasDb.add(index, p);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot snapshot, String previousChildKey) {
                int index = Integer.parseInt(snapshot.getKey());
                switch(tipo) {
                    case "Entrenadores":
                        personasDb.set(index, snapshot.getValue(Entrenador.class));
                        break;
                    case "Deportistas":
                        personasDb.set(index, snapshot.getValue(Deportista.class));
                        break;
                    default:
                        personasDb.set(index, snapshot.getValue(Persona.class));
                        break;
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {
                Persona p;
                switch(tipo) {
                    case "Entrenadores":
                        p = snapshot.getValue(Entrenador.class);
                        break;
                    case "Deportistas":
                        p = snapshot.getValue(Deportista.class);
                        break;
                    default:
                        p = snapshot.getValue(Persona.class);
                        break;
                }
                if(personasDb.contains(p)) {
                    personasDb.remove(p);
                }
            }

            @Override
            public void onChildMoved(DataSnapshot snapshot, String previousChildKey) {
                System.out.println("Snapshot: " + snapshot);
                System.out.println("previousChildKey: " + previousChildKey);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("Hubo un error al obtener datos del servidor: " + firebaseError);
            }
        });

        return personasDb;
    }

    public void disconnect() {
        Firebase.goOffline();
    }

    public void guardarP(ArrayList<Persona> e, String tipo) {
        db.child(tipo).setValue(e);
    }
}
