using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using System;
public class GetData : MonoBehaviour
{
    public Transform spawnPos;
    public GameObject CStolpe;
    public GameObject Rafter;
    public GameObject ShedWall;
    public GameObject ShedWall2;
    public GameObject RoofPlate1;
    public GameObject RoofPlate2;
    private float[] marks = new float[6];

    private string inputvalue;

    public void Get()
    {
        GameObject[] Objects;
        Objects = GameObject.FindGameObjectsWithTag("DestroyThis");
        foreach (GameObject Obj in Objects)
        {
            Destroy(Obj);
        }

        StartCoroutine(GetText());
    }

    public void Update(string newval)
    {
        this.inputvalue = newval;
    }

    IEnumerator GetText()
    {
        UnityWebRequest www = UnityWebRequest.Get("http://128.199.46.149/FOG/FrontController?command=UNITY&order=" + inputvalue);
        yield return www.SendWebRequest();
        Debug.Log(this.inputvalue);
        if (www.isNetworkError || www.isHttpError)
        {
            Debug.Log(www.error);
        }
        else
        {
            // Show results as text

            String txtresp = www.downloadHandler.text;
            Debug.Log(www.downloadHandler.text);
            String[] txt = txtresp.Split(',');
            marks[0] = float.Parse(txt[0]);
            marks[1] = float.Parse(txt[1]);
            marks[2] = float.Parse(txt[2]);
            marks[3] = float.Parse(txt[3]);
            marks[4] = float.Parse(txt[4]);
            marks[5] = float.Parse(txt[5]);


            spawnPos.Rotate(90, 0, 0);
            //spawnPos.localScale = new Vector3(1f, 1f, 1f);

            InstantiateCarport(marks);
            //spawnPos.localScale = new Vector3(2f, 2f, 2f);
            spawnPos.Rotate(-90, 0, 0);




        }
    }

    private void InstantiateCarport(float[] IPS)
    {
        float L = IPS[0] / 1000;
        float W = IPS[1] / 1000;
        float H = IPS[2] / 1000;
        double V = IPS[3] * (Math.PI) / 180;
        float Sl = IPS[4] / 1000;
        float Sw = IPS[5] / 1000;
        double RW = (W / 2) / Math.Cos(V);
        Vector3 Displacement;
        CStolpe.transform.localScale = new Vector3(0.1f, H, 0.1f);
        Rafter.transform.localScale = new Vector3(W, 0.1f, 0.1f);
        RoofPlate1.transform.localScale = new Vector3((float)RW, 0.1f, L);
        RoofPlate2.transform.localScale = new Vector3((float)RW, 0.1f, L);
        ShedWall.transform.localScale = new Vector3(Sl, H, 0.1f);
        ShedWall2.transform.localScale = new Vector3(0.1f, H, Sw);




        for (float i = 0; i <= L; i += 2)
        {

            //Placing Corner pillars
            Displacement = new Vector3(W / 2, H / 2, i);
            Instantiate(CStolpe, spawnPos.position + Displacement, spawnPos.rotation);
            Displacement = new Vector3(-(W / 2), H / 2, i);
            Instantiate(CStolpe, spawnPos.position + Displacement, spawnPos.rotation);
            //Placing Rafters
            Displacement = new Vector3(0f, H, i);
            Instantiate(Rafter, spawnPos.position + Displacement, spawnPos.rotation);
        }

        //Placing Roof:
        float Xd = (W / 4);
        double Yd = (Math.Sin(V) * (W / 2) / Math.Cos(V)) / 2;


        Displacement = new Vector3(W / 2 - Sw / 2, H / 2, 0f);
        GameObject shedwall1 = Instantiate(ShedWall, spawnPos.position + Displacement, spawnPos.rotation);
        Displacement = new Vector3(W / 2 - Sw / 2, H / 2, Sw);
        GameObject shedwall2 = Instantiate(ShedWall, spawnPos.position + Displacement, spawnPos.rotation);
        Displacement = new Vector3((W / 2), H / 2, Sw / 2);
        GameObject shedwall3 = Instantiate(ShedWall2, spawnPos.position + Displacement, spawnPos.rotation);
        Displacement = new Vector3((W / 2) - Sw, H / 2, Sw / 2);
        GameObject shedwall4 = Instantiate(ShedWall2, spawnPos.position + Displacement, spawnPos.rotation);



        Displacement = new Vector3(Xd, Math.Abs((float)Yd + H), (L / 2) - 0.1f);
        GameObject roof1 = Instantiate(RoofPlate1, spawnPos.position + Displacement, spawnPos.rotation);
        Displacement = new Vector3(-Xd, Math.Abs((float)Yd + H), (L / 2) - 0.1f);
        GameObject roof2 = Instantiate(RoofPlate2, spawnPos.position + Displacement, spawnPos.rotation);
        roof1.transform.Rotate(0f, 0f, -IPS[3]);
        roof2.transform.Rotate(0f, 0f, IPS[3]);

        GameObject[] Objects;
        Objects = GameObject.FindGameObjectsWithTag("DestroyThis");
        foreach (GameObject Obj in Objects)
        {
            Obj.transform.parent = spawnPos;
        }
    }
}
//2 stolper per 2 meter