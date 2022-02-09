package com.github.romer533.route

import cats.data.Kleisli
import cats.effect.Async
import com.github.romer533.endpoint.EndpointOps._
import com.github.romer533.service.Service
import org.http4s.implicits._
import org.http4s.{HttpRoutes, Request, Response}
import sttp.tapir.server.http4s.Http4sServerInterpreter

class ServiceRoute[F[_]: Async](service: Service[F]) {

  def routes: Kleisli[F, Request[F], Response[F]] = getTitles.orNotFound

  private def getTitles: HttpRoutes[F] = Http4sServerInterpreter().toRoutes(getEndpoint.serverLogic(urls => service.getTitles(urls.urls)))

}

object ServiceRoute {
  def apply[F[_]: Async](service: Service[F]): ServiceRoute[F] =
    new ServiceRoute[F](service)
}
