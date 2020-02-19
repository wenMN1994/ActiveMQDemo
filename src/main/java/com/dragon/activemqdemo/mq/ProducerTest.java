package com.dragon.activemqdemo.mq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/2/19 15:27
 * @description：生产者
 * @modified By：
 * @version: $
 */
public class ProducerTest {

    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://192.168.163.129:61616");
        //创建连接
        Connection connection = activeMQConnectionFactory.createConnection();
        //启动连接
        connection.start();
        //创建会话
        // 第一个参数表示是否支持事务，false时，第二个参数Session.AUTO_ACKNOWLEDGE，Session.CLIENT_ACKNOWLEDGE，DUPS_OK_ACKNOWLEDGE其中一个
        // 第一个参数设置为true时，第二个参数可以忽略 服务器设置为SESSION_TRANSACTED
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //创建队列
//        Queue queue = session.createQueue("dragon");
        Queue queue = session.createQueue("orders");
        //创建生产者
        MessageProducer producer = session.createProducer(queue);
        //生成消息
//        ActiveMQTextMessage activeMQTextMessage = new ActiveMQTextMessage(); // 纯文本消息
//        activeMQTextMessage.setText("您好！");
        ActiveMQMapMessage activeMQMapMessage = new ActiveMQMapMessage();
        activeMQMapMessage.setString("orderid", "1001");
        activeMQMapMessage.setString("result", "ok");
        //发送消息
//        producer.send(activeMQTextMessage);
        producer.send(activeMQMapMessage);
        //关闭
        producer.close();
        session.close();
        connection.close();
    }
}
