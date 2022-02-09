package com.github.romer533.route

import cats.data.Kleisli
import cats.effect.Async
import com.github.romer533.endpoint.EndpointOps.getEndpoint
import com.github.romer533.service.Service
import org.http4s.implicits.http4sKleisliResponseSyntaxOptionT
import org.http4s.{HttpRoutes, Request, Response}
import sttp.tapir.server.http4s.Http4sServerInterpreter

class ServiceRoute[F[_]: Async](service: Service[F]) {

  def routes: Kleisli[F, Request[F], Response[F]] = aga().orNotFound

  private def aga(): HttpRoutes[F] =
    Http4sServerInterpreter().toRoutes(getEndpoint.serverLogic(titles => service.getTitles(titles.urls)))

}

object ServiceRoute {
  def apply[F[_]: Async](service: Service[F]): ServiceRoute[F] =
    new ServiceRoute[F](service)
}
