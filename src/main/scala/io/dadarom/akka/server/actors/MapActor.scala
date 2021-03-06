package io.dadarom.akka.server.actors

import java.util.StringTokenizer

import akka.actor.{Actor, ActorRef, actorRef2Scala}
import io.dadarom.akka.server._

import scala.collection.immutable._

class MapActor(reduceActor: ActorRef) extends Actor {

  val STOP_WORDS_LIST = List("a", "am", "an", "and", "are", "as", "at", "be",
    "do", "go", "if", "in", "is", "it", "of", "on", "the", "to")

  val defaultCount: Int = 1

  def receive: Receive = {
    case message: String =>
      reduceActor ! evaluateExpression(message)
  }
  def evaluateExpression(line: String): MapData = {
    var dataList = List[Word]()
    var parser: StringTokenizer = new StringTokenizer(line)
    while (parser.hasMoreTokens()) {
      var word: String = parser.nextToken().toLowerCase()
      if (!STOP_WORDS_LIST.contains(word)) {
        dataList = new Word(word, defaultCount) :: dataList
      }
    }
    return new MapData(dataList)
  }
}
