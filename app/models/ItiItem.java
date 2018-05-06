package models;


import java.util.*;
import java.util.regex.Pattern;

import play.modules.mongodb.jackson.MongoDB;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;
import net.vz.mongodb.jackson.DBQuery;

public class ItiItem
{
    public ItiItem()
    {}

    
    public String title  = "";
    public String type = "";
    public String desc = "";
    public String pics = "";
    public String date = "";
    

       
}
