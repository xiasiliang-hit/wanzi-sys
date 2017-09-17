package models;

//import io.ebean.*;
import javax.persistence.*;

//import root.persistence.Entity;
//import root.persistence.Id;
//import com.avaje.ebean.Model;
//import com.avaje.ebean.*;
import java.util.*;
import play.modules.mongodb.jackson.MongoDB;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;
import net.vz.mongodb.jackson.DBQuery;
import org.codehaus.jackson.annotate.JsonProperty;

//@Entity
//@Table(name="alluser")
public class AUser
{

	public AUser()
	{}
	
    public AUser(String name, String email, String password)
    {
	this.name = name;
	this.email = email;
	this.password = password;
    }

	public static List<AUser> all() {
		return AUser.coll.find().toArray();
	}
	
	public static void create(AUser u) {
		AUser.coll.save(u);
	}

	public static AUser verifyUser(String email, String password)
	{
		AUser u = AUser.coll.findOne(DBQuery.is("email", email));
		
		if (u != null && u.password != null)
		{
			if (u.password.equals(password))
				return u;
			else
				return null;
		}
		else
			return null;
	}
	
	public static void delete(String id) {
		AUser u = AUser.coll.findOneById(id);
		if (u != null)
			AUser.coll.remove(u);
	}

	public static void removeAll(){
		AUser.coll.drop();
	}


	//    private static final long serialVersionUID = 1L;
	private static JacksonDBCollection<AUser, String> coll = MongoDB.getCollection("auser", AUser.class, String.class);
	
    @Id
	@ObjectId
    public String id;

    public String name;
    
    public String email;

    public String password;
    
}
