package io.dadarom.akka.server.actors

import akka.actor.Actor
import io.dadarom.akka.server.{Result, ReduceData, MapData, Word}

import scala.collection.immutable._

class AggregateActor extends Actor {

  var finalReducedMap = new HashMap[String, Int]

  def receive: Receive = {
    case message: ReduceData =>
      aggregateInMemoryReduce(message.reduceDataMap)
    case message: Result =>
      println("=============================")
      println(finalReducedMap.toString())
      println("=============================")
  }

  def aggregateInMemoryReduce(reducedMap: Map[String, Int]) {
    var count: Int = 0
    reducedMap.foreach((entry: (String, Int)) =>
      if (finalReducedMap.contains(entry._1)) {
        count = entry._2 + finalReducedMap.get(entry._1).get
        finalReducedMap += entry._1 -> count
      } else
        finalReducedMap += entry._1 -> entry._2)
  }
}