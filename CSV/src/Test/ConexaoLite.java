package Test;

import Controller.Controladora;
import Controller.FabricaConexao;

public class ConexaoLite
{
  public static void main(String[] args)
  {
    //System.out.println(FabricaConexao.conectarSQLITE());
    System.out.println(Controladora.consultarTotalQ());
    System.out.println(Controladora.consultarTotalA());
  }
}
