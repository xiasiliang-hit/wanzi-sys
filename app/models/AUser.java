package models;

import javax.persistence.*;

import java.util.*;
import play.modules.mongodb.jackson.MongoDB;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;
import net.vz.mongodb.jackson.DBQuery;
import org.codehaus.jackson.annotate.JsonProperty;
import java.awt.image.BufferedImage;
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
		AUser u = null;
		try
			{
		 u = AUser.coll.findOne(DBQuery.is("email", email));
			}
		catch (Exception e)
			{
				return null;
			}
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
	public BufferedImage profile_image;


	
	public String type = "";
	public String gender = "";
	public String city_and_country = "";
	public String employer = "";
	public String title = "";

	public BufferedImage passport_image = null;
	public BufferedImage travel_image = null;
	
	public static String GUIDER = "GUIDER";
	public static String TRAVELLER = "TRAVELLER";
}
