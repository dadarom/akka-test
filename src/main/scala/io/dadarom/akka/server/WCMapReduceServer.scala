package io.dadarom.akka.server

import java.io.File

import akka.actor.{Actor, Props, ActorSystem}
import com.typesafe.config.ConfigFactory
import io.dadarom.akka.server.actors.MasterActor

import scala.collection.immutable.{Map, List}

/**
 * Created by leo on 15-12-7.
 */
class Word(val word:String,val count:Int)
case class Result()
class MapData(val dataList: List[Word])
class ReduceData(val reduceDataMap: Map[String, Int])

/**
 * Remote actor which listens on port 5150
 */
class WCMapReduceServer extends Actor {
  val  masterActor = context.actorOf(Props[MasterActor])

  override def receive: Receive = {
    case msg: String => {
//      println("Received: " + msg + " from " + sender)
//      sender ! msg

      if (msg.equals("EOF")){
        println("send Result");
        masterActor ! new Result
        masterActor ! Result
      }
      else
        masterActor ! msg
    }
    case _ => println("Received unknown msg ")
  }
}

object WCMapReduceServer {
  def main(args: Array[String]) {
//    val system = ActorSystem.create("WCMapReduceApp", ConfigFactory.load().getConfig("WCMapReduceApp"));

    val configFile = getClass.getClassLoader.getResource("remote_application.conf").getFile
    val config = ConfigFactory.parseFile(new File(configFile))

    val system = ActorSystem("ServerSystem",config)
    val remote = system.actorOf(Props[WCMapReduceServer], name="remote")

    println("remote is ready")
  }
}

