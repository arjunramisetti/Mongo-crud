package com.iton.vaadinmongosample;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
public class JavaMongoDemo {

	private MongoClient mongoClient;
	private DB db;
	private ArrayList<String> searchList;
	private ArrayList<String> addressList;
	private ArrayList<Address> addressList2;
public JavaMongoDemo() {
	 try {
		mongoClient = new MongoClient("localhost",27017);
		 db = mongoClient.getDB("javatpointdb");
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
}

	public void mongodemo() {
try {
		
//		List l = mongoClient.getCredentialsList();
//		System.out.println("l size is "+l.size());
//		for(int i=0;i<l.size();i++) {
//			System.out.println(l.get(i));
//		}
		String connectPoint  =  mongoClient.getConnectPoint();
		System.out.println("connect point "+ connectPoint);
		
		List<String> dbs = mongoClient.getDatabaseNames();
		for(String dbss : dbs)
		{
			System.out.println(dbss);
		}

		
		Set<String> set = db.getCollectionNames();
		for(String collectionNames : set) {
			System.out.println("collection names are "+ collectionNames);
		}
		DBCollection dbCollection =db.getCollection("javatpoint");
		DBCursor cursor = dbCollection.find();
		while(cursor.hasNext()) {
			System.out.println(""+cursor.next());
		}
//		BasicDBObject dbObject = new BasicDBObject();
//		dbObject.put("name", "car");
//		dbObject.put("brand", "benz");
//		dbObject.put("model", "xyz");
//		DBCollection col = db.createCollection("cars", dbObject);
//		col.insert(dbObject);
		

		
}catch(Exception e) {
	e.printStackTrace();
}
	}
	
	
	public void addFields(User user) {
		try {
        DBCollection counterTable = db.getCollection("counters");
        long count = counterTable.count();
        System.out.println("count value "+count);
      
        long inc_Count = count+1;
        BasicDBObject counterDoc = new BasicDBObject();
        counterDoc.put("_id", "employee_id"+inc_Count);
        counterDoc.put("sequence_value", inc_Count);
        counterTable.insert(counterDoc);
        System.out.println("incremented count value "+inc_Count);
		DBCollection table = db.getCollection("user");
		BasicDBObject doc = new BasicDBObject();
		doc.put("name", user.getName());
		doc.put("salary", user.getSalary());
		doc.put("createdAt", new Date()+"");
		doc.put("e_id", count);
		table.insert(doc);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	 
	public ArrayList<User> getUserCollection(){
		ArrayList<String> list = new ArrayList();
		ArrayList<User> userCollection = new ArrayList<User>();
		String x;
		try {
		
	
		DBCollection table = db.getCollection("user");
		DBCursor cursor = table.find().sort(new BasicDBObject("e_id",-1));
        
		while(cursor.hasNext()) {
System.out.println("xyz");
			list.add(cursor.next()+"");
		}
		
//			table.find().forEach();
		for(String jo:list) {
			User user = new User();
			JSONObject js = new JSONObject(jo);
			System.out.println(" hjhhhh   " +jo);
			JSONObject js1 = js.getJSONObject("_id");
			
            user.setId(js1.getString("$oid"));
            if(js.has("e_id")) {
//            JSONObject js2 = js.getJSONObject("e_id");
            
            	
            long l = Long.parseLong(js.get("e_id")+"");
            user.setEmpId(l);
            }
			user.setName(js.getString("name"));
			user.setSalary(js.getString("salary"));
			
			if(js.has("createdAt")) {
			user.setDate(js.getString("createdAt"));
//			System.out.println("date field "+js.getString("createdAt"));

			}
						userCollection.add(user);
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return userCollection;
	}
	
	
	public void updateDocument(User user,String oldName,String oldSalary) {
		DBCollection table = db.getCollection("user");
		
	/*	BasicDBObject newDoc = new BasicDBObject() ;
		newDoc.append("$set", new BasicDBObject().append("name", user.getName()));
		//newDoc.append("$set", new BasicDBObject().append("name", user.getName()).append("salary", user.getSalary()));
		BasicDBObject searchQuery = new BasicDBObject().append("name",oldName);
		//BasicDBObject searchQuery3 = new BasicDBObject().append("e_id",user.getEmpId());
		//table.update(searchQuery3, newDoc);
		table.update(searchQuery, newDoc);
		BasicDBObject newDoc2 = new BasicDBObject() ;
		
		newDoc2.append("$set", new BasicDBObject().append("salary", user.getSalary()));
		BasicDBObject searchQuery2 = new BasicDBObject().append("salary",oldSalary);
		System.out.println("values "+user+"  "+oldName+"  "+oldSalary);
		table.update(searchQuery2, newDoc2);*/
		
		
		
		
		//Update multiple field in a single document
				DBObject query = new BasicDBObject("e_id", user.getEmpId());
				DBObject update = new BasicDBObject();
				update.put("$set", new BasicDBObject("name",user.getName()).append("salary", user.getSalary()));
				System.out.println("values "+user.getEmpId()+"  "+user.getName()+"  "+user.getSalary());
				table.update(query, update);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	public void deleteDocument(long  id) {
DBCollection table = db.getCollection("user");
		
	System.out.println(id);
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("e_id", id);
		table.remove(searchQuery);
		DBCollection addressTable = db.getCollection("Address");
		BasicDBObject deleteQuery = new BasicDBObject();
		deleteQuery.put("empId", id);
		addressTable.remove(deleteQuery);
		
		
	}
	public List<User> searchQuery(String text) {
		searchList = new ArrayList<>(); 
		ArrayList<User> list1 = new ArrayList();
	try {
		DBCollection table = db.getCollection("user");
		DBCursor cursor = table.find(new BasicDBObject("name", text));//.sort(new BasicDBObject("createdAt", -1));
//		ArrayList t = new ArrayList();
//		t.add(text);
//		DBCursor cursor = table.find(new BasicDBObject("name", new BasicDBObject("$in",t)));
//		BasicDBObject db = new BasicDBObject("", "");
//		DBCursor cursor = table.find(new BasicDBObject("name","$text:{$search: "+text+"}"));//(new BasicDBObject("$text", "{$search: "+text+"}") );
		while(cursor.hasNext()) {
			System.out.println("xyxyxy");
			searchList.add(cursor.next()+"");
		}
		

		for(String jo:searchList) {
			User user = new User();
			JSONObject js = new JSONObject(jo);
//			System.out.println(jo);
			JSONObject js1 = js.getJSONObject("_id");
			
            user.setId(js1.getString("$oid"));
			user.setName(js.getString("name"));
			user.setSalary(js.getString("salary"));
			if(js.has("createdAt")) {
			user.setDate(js.getString("createdAt"));
			System.out.println("date field "+js.getString("createdAt"));

			}
						list1.add(user);
		}
	}catch(Exception e) {
			e.printStackTrace();
		}
	return list1;
	}
	
	
	public void addAddressDetails(Address address) {
//	boolean x =	isCollectionExists(db, "Address");
	
			DBCollection addressColl = db.getCollection("Address");
//			addressColl 
			BasicDBObject doc1 = new BasicDBObject();
//			doc1.put("addressId", "");
			doc1.put("empId", address.getEmpId());
			doc1.put("street", address.getStreet());
			doc1.put("city", address.getCity());
			doc1.put("state", address.getState());
			
			
			addressColl.insert(doc1);
		
	}
	
	
	
	public boolean isCollectionExists(DB db, String collectionName) 
	{
		boolean flag=false;
try {
	DBCollection table = db.getCollection(collectionName);
	
	if(table.count()>0) {
		flag=true;
	}
	
	
   
}
catch(Exception e) {
	e.printStackTrace();
	flag=false;
}
return flag;    
	}

	public ArrayList<Address> getAddressDetails(long empId) {
		// TODO Auto-generated method stub
		DBCollection addressTable = db.getCollection("Address");
		addressList  = new ArrayList<String>();
		addressList2 = new ArrayList<Address>();
		DBCursor cursor = addressTable.find(new BasicDBObject("empId", empId));
		while(cursor.hasNext()) {
//			System.out.println("ss   "+cursor.next());
			addressList.add(cursor.next()+"");
		}
		for(String json:addressList) {
			JSONObject jsonObj = new JSONObject(json);
			System.out.println(jsonObj);
			Address address = new Address();
			JSONObject j = jsonObj.getJSONObject("_id");
			address.setAddressId(j.get("$oid")+"");
			address.setEmpId(jsonObj.getLong("empId"));
			address.setStreet(jsonObj.getString("street"));
			address.setCity(jsonObj.getString("city"));
			address.setState(jsonObj.getString("state"));
			addressList2.add(address);
		}
		return addressList2;
	}

	public void deleteAddress(String currentEmpId) {
		// TODO Auto-generated method stub
		DBCollection addressTable = db.getCollection("Address");
		BasicDBObject deleteQuery = new BasicDBObject();
		deleteQuery.put("_id", new ObjectId(currentEmpId));
//		addressTable.deleteOne(new Document("_id", new ObjectId("57a49c6c33b10927ff09623e")));
		addressTable.remove(deleteQuery);
	}
	
	
	public void updateAddress(Address address) {
		//Update multiple field in a single document
		DBCollection addressTable = db.getCollection("Address");
		DBObject query = new BasicDBObject();//
		query.put("_id", new ObjectId(address.getAddressId()));
		DBObject update = new BasicDBObject();
		update.put("$set", new BasicDBObject("street",address.getStreet()).append("city", address.getCity()).append("state", address.getState()));
		//System.out.println("values "+user.getEmpId()+"  "+user.getName()+"  "+user.getSalary());
		addressTable.update(query, update);
	}
}
