package io.dadarom.akka.client

import akka.actor.{ActorSelection, ActorRef, Actor}

/**
  * Created by leo on 15-12-7.
  */
class ClientActor(serverActor: ActorSelection) extends Actor {
  var start : Long = 0;

  override def receive: Receive = {
     case message: String => {
       serverActor ! message;
     }


   }


   override def preStart: scala.Unit = {
     start = System.currentTimeMillis();
   }

   override def postStop: scala.Unit = {
     start = System.currentTimeMillis();
     val timeSpent = (System.currentTimeMillis - start) / 1000
     println(String.format("\n\tClientActor estimate: \t\t\n\tCalculation time: \t%s Secs", timeSpent+""))
   }

 }
