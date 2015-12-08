package io.dadarom.akka.server.actors

import akka.actor.{Actor, ActorRef, actorRef2Scala}
import io.dadarom.akka.server._
import io.dadarom.akka.server.MapData
import scala.collection.immutable._

class ReduceActor(aggregateActor: ActorRef) extends Actor {

  val defaultCount: Int = 1
  def receive: Receive = {
    case message: MapData =>
      aggregateActor ! reduce(message.dataList)
  }

  def reduce(dataList: List[Word]): ReduceData = {
    var reducedMap = new HashMap[String, Int]
    for (wc: Word <- dataList) {
      var word: String = wc.word
      if (reducedMap.contains(word)) {  
    	  var count:Int = reducedMap.get(word).get + defaultCount
        reducedMap += word -> count
      } else {
        reducedMap += word -> defaultCount
      }
    }
    return new ReduceData(reducedMap)
  }
}