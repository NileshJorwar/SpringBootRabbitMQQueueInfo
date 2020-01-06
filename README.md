## MessageListener from Rabbit
1. To Receive the messages, we have to implement Listener for RabbitConfig
    ``
    @Bean
   	public MappingJackson2MessageConverter consumerJackson2MessageConverter()
   	{
   		return new MappingJackson2MessageConverter();
   	}
   	
   	@Bean
   	MessageHandlerMethodFactory messageHandlerMethodFactory()
   	{
   		DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
   		messageHandlerMethodFactory.setMessageConverter(consumerJackson2MessageConverter());
   		return messageHandlerMethodFactory;
   	}
   	
   	@Override
    	public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    		rabbitListenerEndpointRegistrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }
   
   ``
2. To get the Queue information 
    - Add bean to RabbitMQConfig
    ``
    @Bean
    	public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory)
    	{
    		return new RabbitAdmin(connectionFactory);
    	}
    ``
    - Import org.springframework.amqp.rabbit.connection.ConnectionFactory;
    - Create service to implement the functionality Queue info
    ``
       @Autowired
       RabbitAdmin rabbitAdmin;
       public String queueSize()
       {
           Properties properties;
           properties=rabbitAdmin.getQueueProperties("javainuse.queue");
           //System.out.println("QUeue Size--"+properties.get("QUEUE_MESSAGE_COUNT").toString());
           return "Current Queue Size--"+ properties.get("QUEUE_MESSAGE_COUNT").toString();
       }
    ``
    