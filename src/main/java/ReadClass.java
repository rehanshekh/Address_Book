import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadClass {
    public void readJSON() {
        JSONParser parser = new JSONParser();
        try {
            int size = 0;
            Object obj = parser.parse(new FileReader("contact.json"));
            JSONObject jsonObject = (JSONObject) obj;
            while (size < jsonObject.size()) {
                size++;
                String name = (String) jsonObject.get("Contacts"+size);
                System.out.println(name);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    }

