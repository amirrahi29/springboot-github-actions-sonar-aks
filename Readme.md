```
# run this command on azure terminal (bash) to generate json of AZURE_CREDENTIALS

        only provide subscriptions id after subscriptions/
        -------------------------------------------------
        
        az ad sp create-for-rbac \
        --name "springboot-github-actions-sp" \
        --role Contributor \
        --scopes /subscriptions/dde8a869-f29f-41ae-90ea-0047779211e6 \
        --sdk-auth
```
<img width="1705" height="954" alt="Screenshot 2026-03-30 at 5 12 06 AM" src="https://github.com/user-attachments/assets/45aaa307-1b88-40cd-8a9d-7b1d3153f6bf" />


