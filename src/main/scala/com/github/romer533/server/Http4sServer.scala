package com.github.romer533.server

import cats.effect.kernel.Async
import cats.effect.{Resource, Sync}
import com.github.romer533.config.Config
import com.github.romer533.route.ServiceRoute
import com.github.romer533.service.Service
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.server.Server

import java.util.concurrent.{ExecutorService, Executors}
import scala.concurrent.ExecutionContext

object Http4sServer {

  def apply[F[_]: Async](cfg: Config, service: Service[F]): Resource[F, Server] =
    executionContext().flatMap { implicit ec =>
      BlazeServerBuilder[F].withExecutionContext(ec).bindHttp(cfg.port, cfg.host).withHttpApp(ServiceRoute[F](service).routes).resource
    }

  private def executorServices[F[_]: Sync](executor: => ExecutorService): Resource[F, ExecutorService] =
    Resource.make(Sync[F].delay(executor))(exec => Sync[F].delay(exec.shutdown()))

  private def executionContext[F[_]: Sync](wrapExecutor: => ExecutorService = Executors.newCachedThreadPool()): Resource[F, ExecutionContext] =
    executorServices(wrapExecutor).map(ExecutionContext.fromExecutorService)

}
