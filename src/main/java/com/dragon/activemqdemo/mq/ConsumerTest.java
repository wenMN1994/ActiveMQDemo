package com.dragon.activemqdemo.mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/2/19 16:28
 * @description：消费者
 * @modified By：
 * @version: $
 */
public class ConsumerTest {

    public static void main(String[] args) throws JMSException {

        //创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://192.168.163.129:61616");
        //创建连接
        Connection connection = activeMQConnectionFactory.createConnection();
        //启动连接
        connection.start();
        //创建会话
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //创建队列
//        Queue queue = session.createQueue("dragon");
        Queue queue = session.createQueue("orders");
        //创建消费者
        MessageConsumer consumer = session.createConsumer(queue);
        //获取消息消费
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                /*if (message instanceof TextMessage) {
                    try {
                        String text = ((TextMessage) message).getText();
                        System.out.println("******text="+text);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }*/
                if (message instanceof MapMessage) {
                    try {
                        String orderid = ((MapMessage) message).getString("orderid");
                        String result = ((MapMessage) message).getString("result");
                        System.out.println("******orderid="+orderid);
                        System.out.println("******result="+result);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
