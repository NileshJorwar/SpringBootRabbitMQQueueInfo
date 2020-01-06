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
   
  