import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {ExternalConfiguration, ExternalConfigurationHandlerInterface} from "hal-4-angular";

@Injectable()
export class ExternalConfigurationService implements ExternalConfigurationHandlerInterface
{

  constructor(private http: HttpClient)
  {
  }

  /**
   * @override
   */
  public deserialize(): any
  {
  }

  /**
   * @override
   */
  public getExternalConfiguration(): ExternalConfiguration
  {
    return null;
  }

  /**
   * @override
   */
  public getHttp(): HttpClient
  {
    return this.http;
  }

  /**
   * @override
   */
  public getProxyUri(): string
  {
    return "";
  }

  /**
   * @override
   */
  public getRootUri(): string
  {
    return "http://localhost:8080/api/";
  }

  /**
   * @override
   */
  public serialize(): any
  {
  }

  /**
   * @override
   */
  public setExternalConfiguration(externalConfiguration: ExternalConfiguration): any
  {
  }
}
