package com.kwerlingit;

import org.json.JSONObject;

import java.io.*;
import java.math.BigInteger;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.*;
import java.util.function.BiConsumer;

public class MemKV  implements Serializable {

    final String vNotOfType (String typeName ) {
        return "Value not of type " + typeName + ".";
    }

    private HashMap<String, Object> hm;

    public MemKV(){ hm = new HashMap<String, Object>(); }

    public MemKV(int initialCapacity) { hm = new HashMap<String, Object>(initialCapacity); }

    public MemKV(int initialCapacity, float loadFactor) { hm = new HashMap<String, Object>(initialCapacity, loadFactor); }

    public void load( String fName ) throws IOException, ClassNotFoundException {

        try(          FileInputStream fis = new FileInputStream(fName);
                      ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            hm = (HashMap) ois.readObject();
        }
    }

    public void persist( String fName ) throws IOException {

           try( FileOutputStream fos = new FileOutputStream(fName);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
           ) {
               oos.writeObject(hm);
           }
    }

    public int size()                           { return hm.size();                     }

    public boolean isEmpty()                    { return hm.isEmpty();                  }

    public boolean containsKey(String key)      { return hm.containsKey( key );         }

    public void remove(String key)              {        hm.remove( key );              }

    public void delete(String key)              {        hm.remove( key );              }

    public void clear()                         {        hm.clear();                    }

    public boolean containsValue(Object value)  { return hm.containsValue( value );     }

    public void forEach( BiConsumer<String, Object> action ) { hm.forEach( action) ;}

    public MemKV getCopyOfDB() { return findEntriesByKey( "*" ); }

    public List<String> getListOfKeys() {
        List<String> retVal = new ArrayList<String>();

        hm.forEach( (key, val) -> { retVal.add( key ); } );

        return retVal;
    }

    public void addOtherDB( MemKV mkv ) {
        addOtherDB( mkv, false );
    }

    public void addOtherDB( MemKV mkv, boolean addOnlyNewKeys ) {
        mkv.forEach( ( key, val ) -> {
            if( ! addOnlyNewKeys ) { hm.put( key, val ); }
            else if( ! hm.containsKey( key ) ) { hm.put( key, val ); }
        });
    }

    public MemKV findEntriesByKey(String searchPatter ) {
        MemKV retVal = new MemKV();

        try {
            retVal =  findEntriesByKey( searchPatter, false );
        } catch (Exception e) {
            /* Cannot happen, as serializability is already checked when values are entered initialy */
        }

        return retVal;
    }

    private MemKV findEntriesByKey(String searchPatter, boolean caseInsensitiveSearch ) {
        MemKV retVal = new MemKV();
        
        String regex = getRegExFromSearchPatter( searchPatter );

        for(Map.Entry<String, Object> entry : hm.entrySet()) {
            if( entry.getKey().matches( regex ) ) {
                try {
                    retVal.put( entry.getKey(), entry.getValue());
                } catch (Exception e) {
                    /* Cannot happen, as serializability is already checked when values are entered initialy */
                }
            }
        }

        return retVal;
    }

    protected String getRegExFromSearchPatter(String searchPatter) {
        StringBuilder retVal = new StringBuilder();

        StringCharacterIterator it = new StringCharacterIterator( searchPatter );
        boolean isEscaped = false;
        retVal.append("^");
        while( it.current() != CharacterIterator.DONE) {
            Character c = it.current();
            switch( c.charValue() ) {
                case '\\' :     if( isEscaped ) { retVal.append("\\");  isEscaped = false; }
                                else { isEscaped = true;                                   }
                    break;
                case '?' :      if( isEscaped ) { retVal.append("\\?");  isEscaped = false; }
                                else { retVal.append( "." );                                }
                    break;
                case '*' :      if( isEscaped ) { retVal.append("\\*");  isEscaped = false; }
                                else { retVal.append( ".*" );                               }
                    break;
                case '.' :
                case '+' :
                case '^' :
                case '$' :
                case '(' :
                case ')' :
                case '{' :
                case '}' :
                case '[' :
                case ']' :
                case '|' :      if( isEscaped ) { retVal.append("\\\\").append( c );  isEscaped = false; }
                                else { retVal.append( "\\" ).append( c ); }
                    break;

                default:        if( isEscaped ) { retVal.append("\\").append( c );  isEscaped = false; }
                                else { retVal.append( c );                                             }
                    break;
            }
            it.next();
        }
        retVal.append("$");

        return retVal.toString();
    }

    public interface CheckMapCriteria {
        boolean test(JSONObject jo);
    }
    public MemKV findJsonEntriesByCrit( CheckMapCriteria crit ) {
        MemKV retVal = new MemKV();

        this.forEach((k, jStr) -> {
            JSONObject jObj = new JSONObject( jStr );
            if( crit.test( jObj ) ) {
                try {
                    retVal.put( k, jStr );
                } catch (Exception e) {
                    /* Ignored, as the String value is serializable */
                }
            }

        });
    }


    public void put(String key, Object o) throws Exception {
       if ( o instanceof Serializable ) {
            hm.put(key, o);
        }
        else {
            throw (new Exception("Value needs to be serilizable!"));
        }
    }


    public void putString(String key, String o) {
        hm.put(key, o);
    }

    public void putDouble(String key, Double o) {
        hm.put(key, o);
    }

    public void putDouble(String key, double o) {
            hm.put(key, Double.valueOf( o ));
    }

    public void putFloat(String key, Float o){
        hm.put(key, o);
    }

    public void putFloat(String key, float o) {
        hm.put(key, Float.valueOf( o ));
    }

    public void putInteger(String key, Integer o){
        hm.put(key, o);
    }

    public void putInteger(String key, int o) {
        hm.put(key, Integer.valueOf( o ));
    }

    public void putBoolean(String key, Boolean o){
        hm.put(key, o);
    }

    public void putBoolean(String key, boolean o) {
        hm.put(key, Boolean.valueOf( o ));
    }

    public void putBigInteger(String key, BigInteger o){
        hm.put(key, o);
    }

    public void putJson(String key, Object o) {

        this.putString(key, new JSONObject( o ).toString() );

    }

    public void putJson (Object o){
        String key = UUID.randomUUID().toString();

        this.putJson( key, o);
    }

    public Object get(String key) { return hm.get( key ); }

    public String getString(String key)  throws IllegalArgumentException {
        Object o =  hm.get( key );

        if ( ! (o instanceof String) ) {
            throw(new IllegalArgumentException(vNotOfType("String")));
        }

        return (String) o;
    }

    public Double getDouble(String key)  throws IllegalArgumentException {
        Object o =  hm.get( key );;

        if ( ! (o instanceof Double) ) {
            throw(new IllegalArgumentException(vNotOfType("Double")));
        }

        return (Double) o;
    }
    public Float getFloat(String key)  throws IllegalArgumentException {
        Object o =  hm.get( key );;

        if ( ! (o instanceof Float) ) {
            throw(new IllegalArgumentException(vNotOfType("Float")));
        }

        return (Float) o;
    }
    public Integer getInteger(String key)  throws IllegalArgumentException {
        Object o =  hm.get( key );;

        if ( ! (o instanceof Integer) ) {
            throw(new IllegalArgumentException(vNotOfType("Integer")));
        }

        return (Integer) o;
    }
    public Boolean getBoolean(String key)  throws IllegalArgumentException {
        Object o =  hm.get( key );;

        if ( ! (o instanceof Boolean) ) {
            throw(new IllegalArgumentException(vNotOfType("Boolean")));
        }

        return (Boolean) o;
    }
    public BigInteger getBigInteger(String key)  throws IllegalArgumentException {
        Object o =  hm.get( key );;

        if ( ! (o instanceof BigInteger) ) {
            throw(new IllegalArgumentException(vNotOfType("BigInteger")));
        }

        return (BigInteger) o;
    }

}
/*
    public V getOrDefault(Object key,
                          V defaultValue){}

    public V putIfAbsent(K key,
                         V value){ umbennenen }

    public boolean replace(K key,
                           V oldValue,
                           V newValue){}

    public V computeIfAbsent(K key,
                             Function<? super K,? extends V> mappingFunction){}

    public V computeIfPresent(K key,
                              BiFunction<? super K,? super V,? extends V> remappingFunction){}

    public V compute(K key,
                     BiFunction<? super K,? super V,? extends V> remappingFunction){}

    public void replaceAll(BiFunction<? super K,? super V,? extends V> function){}

 */

class MemKV_Json extends MemKV{
    /*
        THis class is all about the values of the KV store.
        Each value is assumed a JSON string.
        Here we use the JSon library:  https://github.com/stleary/JSON-java


     */


}
