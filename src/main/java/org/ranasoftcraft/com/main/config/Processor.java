package org.ranasoftcraft.com.main.config;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class Processor implements ItemProcessor<Users, Object>{

	@Override
	public Object process(Users item) throws Exception {
		// prepare the file data .. 
		String str = new StringBuffer()
				.append(item.getId())
				.append(",")
				.append(item.getName())
				.append(",")
				.append(item.getSalary())
				.toString();
		return str;
	}

}
