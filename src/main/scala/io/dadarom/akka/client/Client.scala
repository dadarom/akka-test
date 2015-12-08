package io.dadarom.akka.client

import java.io.File

import akka.actor.{ActorRef, Props, ActorSystem}
import akka.kernel.Bootable
import com.typesafe.config.ConfigFactory

/**
 * Created by leo on 15-12-7.
 */
class Client extends Bootable {

  override def startup(): Unit = {

  }

  override def shutdown(): Unit = {

  }
}

object Client {
  def main(args: Array[String]) {
//    val system = ActorSystem.create("ClientApplication", ConfigFactory.load().getConfig("WCMapReduceClientApp"));
////    val remoteActor: ActorRef = system.actorFor(
//////      "akka://WCMapReduceApp@127.0.0.1:2552/user/WCMapReduceActor"
////        "akka.tcp://WCMapReduceApp@127.0.1.1:2552"
////    )
//    val remoteActor = system.actorSelection("akka.tcp://WCMapReduceApp@127.0.1.1:2552/user/remote")

    val configFile = getClass.getClassLoader.getResource("local_application.conf").getFile
    val config = ConfigFactory.parseFile(new File(configFile))
    val system = ActorSystem("ClientSystem",config)

     val remoteActor = system.actorSelection("akka.tcp://ServerSystem@127.0.0.1:5151/user/remote")

    val clientActor: ActorRef       = system.actorOf(Props(new ClientActor(remoteActor)), "clientActor")
    val fileReaderActor: ActorRef   = system.actorOf(Props[FileReadActor], "fileReaderActor")

//    fileReaderActor ! ("Othello.txt", clientActor)
//    fileReaderActor ! "Othello.txt"
    fileReaderActor.tell("Othello.txt", clientActor)
  }
}
