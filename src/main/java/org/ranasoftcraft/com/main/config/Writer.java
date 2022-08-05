package org.ranasoftcraft.com.main.config;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class Writer implements ItemWriter{

	@Override
	public void write(List items) throws Exception {
		items.forEach(item->{
			System.out.println(item);
		});
	}

}
