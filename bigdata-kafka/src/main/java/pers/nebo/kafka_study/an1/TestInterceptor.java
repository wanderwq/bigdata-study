package pers.nebo.kafka_study.an1;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestInterceptor implements Interceptor {
	TestProducer  tp =  new TestProducer ();
	 private static final Logger logger = LoggerFactory  
	            .getLogger(TestInterceptor.class);  
	@Override
	public void initialize() {

	}

	@Override
	public Event intercept(Event event) {
		 
		
		String value = new String(event.getBody()) ;
		String key =null;
		 //获取uid,生产者推送
		 String regex = ":(\\\\d+|\\w+)";
		 Pattern p = Pattern.compile(regex);
		 Matcher m =p.matcher(value);
		  if(m.find()) {			  
			  key =  m.group(1).toString();
			  logger.info( "================================key》"+key);
		  }
		  logger.info( "=============================++++value》"+value);
		tp.sendMessgae(key, value);
		return event;
	}

	@Override
	public List<Event> intercept(List<Event> events) {
		List intercepted = new ArrayList<>(events.size());
		for (Event event : events) {
		Event interceptedEvent = intercept(event);
		if (interceptedEvent != null) {
		intercepted.add(interceptedEvent);
		}
		}
		return intercepted;
	 
	}

	@Override
	public void close() {
		tp.close();
		
	}

	  public static class Builder implements Interceptor.Builder{
		@Override
		public void configure(Context context) {

		}
		@Override
		public Interceptor build() {
			return new TestInterceptor();
		}
	  }

}
