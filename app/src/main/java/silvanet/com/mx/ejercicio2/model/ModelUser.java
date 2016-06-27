package silvanet.com.mx.ejercicio2.model;

/**
 * Created by LuisAlfredoSilva on 24/06/2016.
 */
public class ModelUser {
    public int id;
    public String userName;
    public String password;
    public String last_session;
    public String time_in;
    public String time_stamp;


    public ModelUser(int id, String userName, String password, String last_session,String time_in,String time_stamp) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.last_session=last_session;
        this.time_in=time_in;
        this.time_stamp=time_stamp;
    }
}
