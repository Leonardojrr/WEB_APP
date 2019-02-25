/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author Hijos
 */
public class SuperMapper {
      private ObjectMapper objMap = new ObjectMapper();

    public SuperMapper() {
    }
  public <T> T jsonToPlainObj(HttpServletRequest request, Class clase) throws IOException{
      return (T) objMap.readValue(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())), clase);
  }
    
}
