package drakegens.traveljournyl;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ViewRSSFeedActivity extends AppCompatActivity {

    private String rssResult = "";
    boolean item = false;
    // private TextView tvRSSResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rssfeed);

        AsyncRSSFeedParser arfp = new AsyncRSSFeedParser();
        arfp.execute();
        //  tvRSSResult.setText(rssResult);

    }

    private class AsyncRSSFeedParser extends AsyncTask<String, String, String> {

        private static final String urlString = "http://feeds.bbci.co.uk/news/world/rss.xml";
        TextView tvRSSResult = (TextView) findViewById(R.id.RSSfeed);


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvRSSResult.setText("Loading...");
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                URL rssUrl = new URL(urlString);
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser saxParser = factory.newSAXParser();
                XMLReader xmlReader = saxParser.getXMLReader();
                RSSHandler rssHandler = new RSSHandler();
                xmlReader.setContentHandler(rssHandler);
                InputSource inputSource = new InputSource(rssUrl.openStream());
                xmlReader.parse(inputSource);

            } catch (IOException e) {

                e.printStackTrace();
            } catch (SAXException e) {

                e.printStackTrace();
            } catch (ParserConfigurationException e) {

                e.printStackTrace();
            }
            Log.d("Debug", rssResult);

            return null;
        }

        @Override
        protected void onPostExecute(final String s) {
            super.onPostExecute(s);
            tvRSSResult.setText(rssResult);


        }
    }


    private class RSSHandler extends DefaultHandler {

        public void startElement(String uri, String localName, String qName,
                                 Attributes attrs) throws SAXException {
            if (localName.equals("item"))
                item = true;

            if (!localName.equals("item") && item == true)
                rssResult = rssResult + localName + ": ";

        }

        public void endElement(String namespaceURI, String localName,
                               String qName) throws SAXException {

        }

        public void characters(char[] ch, int start, int length)
                throws SAXException {
            String cdata = new String(ch, start, length);
            if (item == true)
                rssResult = rssResult + (cdata.trim()).replaceAll("\\s+", " ") + "\t" + "\n";

        }

    }

}



