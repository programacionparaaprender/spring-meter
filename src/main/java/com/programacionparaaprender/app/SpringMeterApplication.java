package com.programacionparaaprender.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.Meter.Type;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.config.NamingConvention;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

@SpringBootApplication
public class SpringMeterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMeterApplication.class, args);
    	/*
		if(args.length > 0) {
    		System.out.println(args[0] + " " + args[1]);	
    	}
    	prueba5(args);
    	*/
    }
    
    public static void prueba5(String[] args){
        System.out.println("Hola mundo");
        MeterRegistry registry = new SimpleMeterRegistry();
        List<String> list = new ArrayList<>(4);
        Gauge gauge = Gauge.builder("list.size", list, List::size).register(registry);
        list.addAll(Arrays.asList("Devs4j", "raidentrance", "HolaMundo"));
        System.out.println(gauge.value());
    }
    
    public static void timers(String[] args){
        System.out.println("Hola mundo");
        MeterRegistry registry = new SimpleMeterRegistry();
        Timer timer = registry.timer("execution.time");
        timer.record(() -> {
        	for(int i = 0; i < 100; i++) {
        		System.out.println(i);
        	}
        });
        System.out.println(timer.totalTime(TimeUnit.MILLISECONDS));
    }
    
    public static void counters(String[] args){
        System.out.println("Hola mundo");
        MeterRegistry registry = new SimpleMeterRegistry();
        Counter counter2 = registry.counter("devs4j.students", "course", "Méttricas con Micrometer");
        Counter counter = Counter.builder("devs4j.students")
        		.description("Número de estudiantes de devs4j")
        		.tag("components", "Métricas con Micrometer")
        		.register(registry);
        counter.increment();
        counter.increment(200);
        counter2.increment();
        counter2.increment(150);
        System.out.println(counter.count());
        System.out.println(counter2.count());
    }
    
    public static void prueba1(String[] args){
        System.out.println("Hola mundo");
        SimpleMeterRegistry registry = new SimpleMeterRegistry();
        Counter counter = registry.counter("numero.empleados", "oficina", "Benito Juarez");
        counter.increment();
        counter.increment(200);
        System.out.printf("Número de empleados: %f\n", counter.count());
    }
    
    public static void prueba2(String[] args){
        System.out.println("Hola mundo");
        //CompositeMeterRegistry compositeMeter = new CompositeMeterRegistry();
        CompositeMeterRegistry compositeMeter = Metrics.globalRegistry;
        Counter counter = compositeMeter.counter("numero.empleados", "oficina", "Benito Juarez");
        counter.increment();
        counter.increment(200);
        SimpleMeterRegistry registry = new SimpleMeterRegistry();
        SimpleMeterRegistry registry2 = new SimpleMeterRegistry();
        compositeMeter.add(registry);
        compositeMeter.add(registry2);
        counter.increment();
        counter.increment(200);
        System.out.printf("Número de empleados: %f\n", counter.count());
        foo(args);
    }
    
    public static void foo(String[] args){
        CompositeMeterRegistry globalregistry = Metrics.globalRegistry;
        globalregistry.config().namingConvention(new NamingConvention() {
        	@Override
        	public String name(String name, Type type, String baseUnit) {
        		return null;
        	}
        });
        Counter counter = globalregistry.counter("numero.empleados", "oficina", "Benito Juarez");
        counter.increment(150);
        System.out.printf("Número de empleados: %f\n", counter.count());
    }

}
