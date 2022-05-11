import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONParsing {

    public void parseJSON() throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("C:\\Users\\Admin\\Downloads\\sample.json"));
        JSONArray lotNumbers = new JSONArray();
        JSONObject lotNumbersResponse = jsonObject;
        JSONArray itemsArray = (JSONArray) lotNumbersResponse.get("items");
        for (int k = 0; k < itemsArray.size(); k++) {
            JSONObject lotObject = (JSONObject) itemsArray.get(k);
            JSONArray lotArray = (JSONArray) lotObject.get("lots");
            for (int m = 0; m < lotArray.size(); k++) {
                JSONObject lotDetails = (JSONObject) lotArray.get(0);
                String lotNumber = (String) lotObject.get("LotNumber");

            }

        }
    }

}
