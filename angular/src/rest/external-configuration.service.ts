import {ExternalConfiguration, ExternalConfigurationHandlerInterface} from "angular4-hal";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class ExternalConfigurationService implements ExternalConfigurationHandlerInterface
{

  constructor(private http: HttpClient)
  {
  }

  public deserialize(): any
  {
  }

  public getExternalConfiguration(): ExternalConfiguration
  {
    return null;
  }

  public getHttp(): HttpClient
  {
    return this.http;
  }

  public getProxyUri(): string
  {
    return "";
  }

  public getRootUri(): string
  {
    return "http://localhost:8080/api/";
  }

  public serialize(): any
  {
  }

  public setExternalConfiguration(externalConfiguration: ExternalConfiguration): any
  {
  }
}
