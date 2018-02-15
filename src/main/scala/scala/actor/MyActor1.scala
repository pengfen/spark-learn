package scala.actor

import scala.actors.Actor


object MyActor1 extends Actor{
  //重新act方法
  def act(){
    for(i <- 1 to 10){
      println("actor-1 " + i)
      Thread.sleep(2000)
    }
  }
}
