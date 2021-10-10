package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since J2SE-1.8
 CREATE TABLE game (
  id INT NOT NULL AUTO_INCREMENT,    
  name VARCHAR(30) NOT NULL,   
  releasedate VARCHAR(30) NOT NULL,
  gamesystem VARCHAR(30) NOT NULL,
  publisher VARCHAR(30) NOT NULL,
  price VARCHAR(30) NOT NULL,
  region VARCHAR(10) NOT NULL, 
  PRIMARY KEY (id));
 */
@Entity
@Table(name = "game")
public class Game {

@Id  // primary key
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id") // specify the column name. Without it, it will use method name
   private Integer id;

   @Column(name = "name")
   private String name;

   @Column(name = "releasedate")
   private String releasedate;
   
   @Column(name = "gamesystem")
   private String gamesystem;
   
   @Column(name = "publisher")
   private String publisher;
   
   @Column(name = "price")
   private String price;
   
   @Column(name = "region")
   private String region;
   

   public Game() {
   }
   
   public Game(Integer id, String name, String releasedate, String gamesystem, String publisher, String price,
			String region) {
		this.id = id;
		this.name = name;
		this.releasedate = releasedate;
		this.gamesystem = gamesystem;
		this.publisher = publisher;
		this.price = price;
		this.region = region;
	}

   public Game(String name, String releasedate, String gamesystem, String publisher, String price,
			String region) {
		this.name = name;
		this.releasedate = releasedate;
		this.gamesystem = gamesystem;
		this.publisher = publisher;
		this.price = price;
		this.region = region;
	}
   
   
   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

public String getReleasedate() {
	return releasedate;
}

public void setReleasedate(String releasedate) {
	this.releasedate = releasedate;
}

public String getGamesystem() {
	return gamesystem;
}

public void setGamesystem(String gamesystem) {
	this.gamesystem = gamesystem;
}

public String getPublisher() {
	return publisher;
}

public void setPublisher(String publisher) {
	this.publisher = publisher;
}

public String getPrice() {
	return price;
}

public void setPrice(String price) {
	this.price = price;
}

public String getRegion() {
	return region;
}

public void setRegion(String region) {
	this.region = region;
}

@Override
public String toString() {
	return "Game: " + id + ", " + name + ", " + releasedate + ", " + gamesystem
			+ ", " + publisher + ", " + price + ", " + region;
}
}