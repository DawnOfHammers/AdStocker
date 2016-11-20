package TwitterData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import twitter4j.JSONObject;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterApp {
	public static void main(String[] args) throws TwitterException{
		JSONObject obj = new JSONObject();
		
		ConfigurationBuilder cf = new ConfigurationBuilder();
		cf.setDebugEnabled(true)
				.setOAuthConsumerKey("1F50rEDR4rsevy0yDMyzVNiH1")
				.setOAuthConsumerSecret("D49y2cgy0cqegJGY094EnApMk0KAe2Be6k834E0uZ4Nx5WZS9q")
				.setOAuthAccessToken("4532155512-DUnATy53M3vI5XhdaypbXmFfBbRlnaVzyW7f4WE")
				.setOAuthAccessTokenSecret("iHjaEASicgMnDMkDDgJ32jwbkYlrrsQ1RrHsw5JI5xkNN");
		TwitterFactory tf = new TwitterFactory(cf.build());
		Twitter twitter = tf.getInstance();
		 
		String [] tag = {"Sports", "Politics", "Entertainment", "Business"};
		BufferedWriter bw = null;
		String text;
		try {
			for (int i = 0; i<tag.length; i++) {
				bw = new BufferedWriter(new FileWriter(tag[i]+".txt"));
				Query query = new Query("#"+tag[i]);
				query.count(40);
				QueryResult result = twitter.search(query);
				for (Status st: result.getTweets()) {
					text = st.getText();
					if (text.startsWith("RT")){
						String [] info = text.split(":", 2);
						//System.out.println(info[1]);
						bw.write(info[1]);
						bw.newLine();
					}
			}
				bw.close();
			}
		} catch (TwitterException te) {
			System.out.println("Failed to search tweets: "+te.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
