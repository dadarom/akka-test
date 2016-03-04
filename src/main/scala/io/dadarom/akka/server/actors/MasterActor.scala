package io.dadarom.akka.server.actors

import akka.actor.{Actor, ActorRef, Props, actorRef2Scala}
//import io.dadarom.akka.server._
import io.dadarom.akka.server.{Result, ReduceData}

class MasterActor extends Actor {
  val aggregateActor: ActorRef = context.actorOf(Props[AggregateActor], name = "aggregate")
  val reduceActor: ActorRef = context.actorOf(Props(new ReduceActor(aggregateActor)), name = "reduce")
  val mapActor: ActorRef = context.actorOf(Props(new MapActor(reduceActor)), name = "map")

  def receive: Receive = {
    case message: String =>
//      println(message)
      mapActor ! message

    case message: Result => {
      println("rece RESULT")
      aggregateActor ! message
    }

    case default => {
      println("------default-------")
      println(default)
      println("------default-------")
    }

  }
}