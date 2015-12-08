package io.dadarom.akka.client

import akka.actor.Actor

/**
 * Created by leo on 15-12-7.
 */
class FileReadActor extends Actor {

  override def receive: Receive = {
    case message: String => {

      println("Sender: "+ sender)
      val fileName: String = message.asInstanceOf[String]
      val file = getClass.getClassLoader.getResource(fileName).getFile
      for (line <- scala.io.Source.fromFile(file).getLines()) {
        sender ! line;
      }
      System.out.println("All lines send !");
      // send the EOF message..
      sender ! "EOF";
    }

  }
}
