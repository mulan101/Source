package jeho.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jeho.pojo.Greeting;
import jeho.pojo.Person;

@RestController
@RequestMapping(value="/test", method = {RequestMethod.POST})
public class GreetingController {

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestBody Person person) {
        Greeting greeting = new Greeting();
        greeting.setContents(person.getName() + " , " + person.getAge() + " , " + person.getEmail());
    	
    	return greeting;
    }
}