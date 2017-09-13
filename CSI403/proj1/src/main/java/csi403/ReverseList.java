package csi403;


// Import required java libraries
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.json.*;


// Extend HttpServlet class
public class ReverseList extends HttpServlet {

  private static final int JsonArrayBuilder = 0;

// Standard servlet method 
  public void init() throws ServletException
  {
      // Do any required initialization here - likely none
  }

  // Standard servlet method - we will handle a POST operation
  public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      doService(request, response); 
  }

  // Standard servlet method - we will not respond to GET
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      // Set response content type and return an error message
      response.setContentType("application/json");
      PrintWriter out = response.getWriter();
      out.println("{ 'message' : 'Use POST!'}");
  }


  // Our main worker method
  // Parses messages e.g. {"inList" : [5, 32, 3, 12]}
  // Returns the list reversed.   
  private void doService(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      // Get received JSON data from HTTP request
      BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
      String jsonStr = "";
      if(br != null){
          jsonStr = br.readLine();
      }
      
      try {
      // Create JsonReader object
      StringReader strReader = new StringReader(jsonStr);
      JsonReader reader = Json.createReader(strReader);
      JsonObject obj = reader.readObject();
      
      // Get the singular JSON object (name:value pair) in this message.    
     
      // From the object get the array named "inList"
      JsonArray inArray = obj.getJsonArray("inList");
      
      
      
      ArrayList<Integer> array = new ArrayList<Integer>();
      
      //store json array values into arraylist
      for(int i = 0; i <= inArray.size()-1; i++) {
    	 array.add(i, inArray.getInt(i));
      };
      
      //Calling my sorting algorithm + getting the time it takes
      
      Date a = new Date();
      quickSort(array, 0, array.size()-1);
      Date b = new Date();
      long ms = (int) (b.getTime()-a.getTime());
      
      
      

     // Reverse the data in the list
      JsonArrayBuilder outArrayBuilder = Json.createArrayBuilder();
     for (int i = 0; i <= array.size()-1; i++) {
         outArrayBuilder.add(array.get(i));
      }

     response.setContentType("application/json");
     PrintWriter out = response.getWriter(); 
      out.println("{ \"outList\" : " + outArrayBuilder.build().toString() + "\n"
    		  + "\"algorithm\" : " + "\"quicksort\"" + "," + "\n"
    		  + "\"timeMS\" : " + ms + " }");
      }
  catch (javax.json.stream.JsonParsingException e) {
	  response.setContentType("application/json");
	     PrintWriter out = response.getWriter(); 
	     out.println("{ \"message\" : " + "Malformed JSON"+ "}");
  }
  catch(NullPointerException e) {
	  response.setContentType("application/json");
	     PrintWriter out = response.getWriter(); 
	     out.println("{ \"message\" : " + "Cannot find inList"+ "}");
      }
  catch(ClassCastException e) {
	  response.setContentType("application/json");
	     PrintWriter out = response.getWriter(); 
	     out.println("{ \"message\" : " + "Values are not valid for sorting" + "}");
  }
  catch(javax.json.JsonException e) {
	  response.setContentType("application/json");
	     PrintWriter out = response.getWriter(); 
	     out.println("{ \"message\" : " + "Cannot read JSON object" + "}");
  }
  }
    
  // Standard Servlet method
  public void destroy()
  {
      // Do any required tear-down here, likely nothing.
  }

private void quickSort(ArrayList<Integer> array, int start, int end) {
	int pIndex;
	
	if(start < end) {
		pIndex = partition(array, start, end);
		quickSort(array, start, pIndex - 1);
		quickSort(array, pIndex + 1, end);
	}
}

private int partition(ArrayList<Integer> array, int start, int end) {
	// TODO Auto-generated method stub
	int x = end;
	int i = start - 1;
	int temp;
	
	for(int j = start; j <= end-1; j++) {
		if(array.get(j) <= array.get(x)) {
			i++;
			temp = array.get(j);
			array.set(j, array.get(i));
			array.set(i, temp);
			temp = 0;
		}
	}
	
	temp = array.get(i+1);
	array.set(i+1, array.get(x));
	array.set(end, temp);
	temp = 0;
	
	return i+1;
}
}

